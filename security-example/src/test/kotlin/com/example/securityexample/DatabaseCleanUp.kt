package com.example.securityexample

import jakarta.annotation.PostConstruct
import jakarta.persistence.Entity
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.Table
import jakarta.persistence.metamodel.EntityType
import jakarta.transaction.Transactional
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component



@Component
class DatabaseCleanup : InitializingBean {

    @PersistenceContext
    private val entityManager: EntityManager? = null
    private var tableNames: MutableList<String>? = null
    @PostConstruct
    override fun afterPropertiesSet() {
        val entities: Set<EntityType<*>> = entityManager?.metamodel?.entities ?: emptySet()

        val tableNamesList: MutableList<String> = entities.asSequence()
                .filter { isEntity(it) && hasTableAnnotation(it) }
                .map { entityType ->
                    entityType.javaType.getAnnotation(Table::class.java)?.name
                            ?: convertCamelCaseToSnakeCase(entityType.name)
                }.toMutableList()

        val entityNames: List<String> = entities.asSequence()
                .filter { isEntity(it) && !hasTableAnnotation(it) }
                .map { entityType ->
                    convertCamelCaseToSnakeCase(entityType.name)
                }.toList()

        tableNamesList.addAll(entityNames)
        tableNames = tableNamesList
    }

    private fun isEntity(e: EntityType<*>): Boolean {
        return e.javaType.getAnnotation(Entity::class.java) != null
    }

    private fun hasTableAnnotation(e: EntityType<*>): Boolean {
        return e.javaType.getAnnotation(Table::class.java) != null
    }

    private fun convertCamelCaseToSnakeCase(name: String): String {
        return name.split("(?=[A-Z])".toRegex()).joinToString("_").lowercase()
    }

    @Transactional
    fun execute() {
        entityManager?.let { em ->
            em.flush()
            // Disable foreign key checks
            em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate()

            // Reset each table
            tableNames?.forEach { tableName ->
                if (tableName.matches(Regex("^[a-zA-Z0-9_]+$"))) { // Validate table names against SQL injection
                    em.createNativeQuery("TRUNCATE TABLE $tableName").executeUpdate()
                    em.createNativeQuery("ALTER TABLE $tableName AUTO_INCREMENT = 1").executeUpdate()
                }
            }

            // Re-enable foreign key checks
            em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate()
        } ?: error("EntityManager is not initialized.")
    }
}