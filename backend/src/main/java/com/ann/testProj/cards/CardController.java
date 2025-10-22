package com.ann.testProj.cards;

import org.springframework.web.bind.annotation.*;
import java.util.List;

/** 3
 * 🧩 CardController — это REST-контроллер, который отвечает за обработку HTTP-запросов.
 *
 * Он принимает запросы от клиента (например, Postman, браузер, frontend)
 * и вызывает нужные методы репозитория (CardRepository),
 * чтобы получить, создать, обновить или удалить карточки в базе данных.
 *
 * 💡 Проще говоря:
 * Это "официант" между пользователем и базой данных.
 * Пользователь → контроллер → репозиторий → база данных → обратно результат.
 *
 * 📘 Основные аннотации:
 * - @RestController — говорит Spring, что этот класс обрабатывает REST API (и возвращает JSON)
 * - @RequestMapping("/cards") — общий путь для всех запросов к карточкам
 * - @GetMapping, @PostMapping, @PutMapping, @DeleteMapping — указывают тип запроса и маршрут
 *
 * ✅ Пример:
 *   GET http://localhost:8080/cards       → получить список всех карточек
 *   POST http://localhost:8080/cards      → создать новую карточку
 *   GET http://localhost:8080/cards/1     → получить карточку с id=1
 *   PUT http://localhost:8080/cards/1     → обновить карточку с id=1
 *   DELETE http://localhost:8080/cards/1  → удалить карточку с id=1
 */

@RestController // Указывает, что класс будет обрабатывать REST-запросы и возвращать JSON
@RequestMapping("/cards") // Базовый путь для всех эндпоинтов
public class CardController {

    // Репозиторий для доступа к базе данных
    private final CardRepository repo;

    // Через конструктор Spring "внедряет" (injection) нужный репозиторий
    public CardController(CardRepository repo) {
        this.repo = repo;
    }

    /**
     * 📄 GET /cards
     * Получить список всех карточек из базы данных.
     * Возвращает JSON-массив всех объектов Card.
     */
    @GetMapping
    public List<Card> getAll() {
        return repo.findAll();
    }

    /**
     * ➕ POST /cards
     * Создать новую карточку.
     * @RequestBody — значит, что данные приходят в JSON из запроса (например, из Postman).
     * Пример тела запроса:
     * {
     *   "title": "New Task",
     *   "description": "Description text",
     *   "status": "OPEN"
     * }
     * или
     * curl -X POST http://localhost:8080/cards \
     *   -H "Content-Type: application/json" \
     *   -d '{"title":"New Card","description":"Created from terminal","status":"OPEN"}'
     */
    @PostMapping
    public Card create(@RequestBody Card card) {
        return repo.save(card); // Сохраняет новую запись в базе и возвращает её обратно
    }

    /**
     * 🔍 GET /cards/{id}
     * Получить одну карточку по её ID.
     * @PathVariable Long id — означает, что значение {id} из URL попадёт в параметр метода.
     * Пример: GET /cards/1 → id = 1
     */
    @GetMapping("/{id}")
    public Card getOne(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(); // Если не найдена — выбросит ошибку 404
    }

    /**
     * ♻️ PUT /cards/{id}
     * Обновить карточку с указанным ID.
     * Тело запроса (JSON) содержит новые данные.
     * Мы устанавливаем тот же id и сохраняем карточку снова.
     */
    @PutMapping("/{id}")
    public Card update(@PathVariable Long id, @RequestBody Card updated) {
        updated.setId(id); // Чтобы точно обновить, а не создать новую
        return repo.save(updated); // Сохраняет изменения
    }

    /**
     * ❌ DELETE /cards/{id}
     * Удалить карточку по ID.
     * После вызова карточка исчезает из базы.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
