package ru.otus.cars


interface Tank {
    val mouth: TankMouth
    fun getContents(): Int
    fun receiveFuel(liters: Int)
}

abstract class TankMouth {
    private var opened = false
    fun open() {
        if (opened) {
            println("Крышка бака уже открыта")
        }
        else {
            println("Открыли крышку бака")
            opened = true
        }
    }

    fun close() {
        if (!opened) {
            println("Крышка бака уже закрыта")
        }
        else {
            println("Закрыли крышку бака")
            opened = false
        }
    }

    protected fun opened(): Boolean = opened
}


class FuelTank(override val mouth: TankMouth, private val capacity: Int) : Tank {
    private var contents: Int = 0
    override fun getContents() = contents
    override fun receiveFuel(liters: Int) {
        val remaining = capacity-contents

        if (remaining > 0) {
            if (liters >= remaining) {
                contents += remaining
                println("Заправили $remaining л до полного бака")
            } else {
                contents += liters
                println("Заправили $liters л")
            }
        }
        else {
            println("Бак полон")
        }
    }
}


class PetrolMouth : TankMouth() {
    private lateinit var tank: FuelTank
    fun attachTo(tank: FuelTank) {
        this.tank = tank
    }
    fun fuelPetrol(liters: Int) {
        if (!opened())
            throw Error("Крышка бака не открыта")

        println("Закачиваем $liters л бензина")
        tank.receiveFuel(liters)
    }
}

class LpgMouth : TankMouth() {
    private lateinit var tank: FuelTank
    fun attachTo(tank: FuelTank) {
        this.tank = tank
    }
    fun fuelLpg(liters: Int) {
        if (!opened())
            throw Error("Крышка бака не открыта")

        println("Закачиваем $liters л газа")
        tank.receiveFuel(liters)
    }
}
