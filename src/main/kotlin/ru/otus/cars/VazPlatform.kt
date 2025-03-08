package ru.otus.cars

abstract class VazPlatform(override val color: String) : Car {
    // Положение руля. Доступно только внутри класса и наследникам
    protected var wheelAngle: Int = 0 // Положение руля

    // Реализация интерфейса CarInput
    override fun wheelToRight(degrees: Int) { wheelAngle += degrees }
    // Реализация интерфейса CarInput
    override fun wheelToLeft(degrees: Int) { wheelAngle -= degrees }

    // Получить оборудование
    override fun getEquipment(): String = "Кузов, колеса, движок, топливный бак"

    // Абстрактное свойство двигателя
    abstract val engine: VazEngine

    // Абстрактное свойство топливного бака
    abstract val tank: FuelTank
}

// Перечисление двигателей ВАЗ
sealed class VazEngine {
    // Объем двигателя можно задать при производстве
    abstract val volume: Int

    data class LADA_2107(override val volume: Int) : VazEngine()
    data class SAMARA_2108(override val volume: Int) : VazEngine()
}


