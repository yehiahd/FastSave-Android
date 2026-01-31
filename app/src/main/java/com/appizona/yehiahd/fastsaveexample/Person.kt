package com.appizona.yehiahd.fastsaveexample

class Person {
    var id: Int = 0
        private set
    var name: String? = null
        private set
    var isEmployee: Boolean = false
        private set

    fun setId(id: Int): Person {
        this.id = id
        return this
    }

    fun setName(name: String): Person {
        this.name = name
        return this
    }

    fun setEmployee(employee: Boolean): Person {
        this.isEmployee = employee
        return this
    }

    override fun toString(): String {
        return "id = $id name = $name isEmployee = $isEmployee"
    }
}
