package com.ann.testProj.cards;

// Импорты для аннотаций JPA (работа с базой данных)
import jakarta.persistence.*;
// Импорт Lombok — чтобы не писать вручную геттеры, сеттеры, toString()
import lombok.Data;

/** 1
 * 🧩 Card — это основной класс-модель (Entity), с которого начинается логика приложения.
 *
 * Он описывает структуру одной карточки, с которой мы работаем.
 * Каждый объект этого класса = одна запись в таблице базы данных.
 *
 * 💡 Проще говоря: это "конструктор" для наших карточек.
 *
 * Пример:
 * Card card = new Card();
 * card.setTitle("Test card");
 * card.setStatus("OPEN");
 *
 * 📘 Здесь мы используем:
 *  - @Entity — говорит Spring, что этот класс нужно хранить в базе (создать таблицу card)
 *  - @Id — указывает, какое поле является уникальным идентификатором
 *  - @GeneratedValue — говорит, чтобы id генерировался автоматически
 *  - @Data (из Lombok) — автоматически создаёт геттеры, сеттеры, equals, hashCode и toString
 */
@Entity
@Data
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String status;
}
