package com.ann.testProj.cards;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;

/** 4
 * 🧩 CardPageController — контроллер, который управляет HTML-страницами (UI),
 * а не JSON-ответами. Он работает с шаблонами Thymeleaf, например:
 *   - templates/cards.html — список карточек
 *   - templates/card-view.html — страница одной карточки
 *
 * 💡 Этот контроллер нужен, чтобы пользователь мог взаимодействовать
 * с карточками через веб-интерфейс:
 *  - видеть таблицу карточек
 *  - добавлять карточку через форму
 *  - открывать отдельную карточку
 *  - удалять карточку
 *
 * 🔄 В отличие от REST-контроллера (CardController), этот класс не возвращает JSON.
 * Он возвращает имя HTML-шаблона, а Spring подставляет в него данные через `Model`.
 */

@Controller // Говорим Spring, что этот класс управляет HTML-страницами (через шаблоны)
@RequestMapping("/ui") // Базовый путь: все URL начинаются с /ui
public class CardPageController {

    // Репозиторий для доступа к данным карточек
    private final CardRepository repo;

    // Через конструктор Spring "внедряет" (инжектит) CardRepository
    public CardPageController(CardRepository repo) {
        this.repo = repo;
    }

    /**
     * 🌍 GET /ui  или /ui/
     * Перенаправляет пользователя с корня UI на страницу со списком карточек.
     * redirect:/ui/cards — это перенаправление (HTTP 302).
     */
    @GetMapping({"", "/"})
    public String root() {
        return "redirect:/ui/cards"; // Переход на страницу списка карточек
    }

    /**
     * 📋 GET /ui/cards
     * Показывает страницу со всеми карточками и кнопкой "Add".
     *
     * Model — это объект, через который передаются данные в HTML-шаблон.
     * Здесь мы передаём:
     *  - список всех карточек (cards)
     *  - пустой объект Card для формы добавления (card)
     *
     * Возвращаем "cards" → Spring ищет файл templates/cards.html
     */
    @GetMapping("/cards")
    public String list(Model model) {
        model.addAttribute("cards", repo.findAll()); // все карточки
        model.addAttribute("card", new Card());      // пустой объект для формы "Add"
        return "cards"; // шаблон templates/cards.html
    }

    /**
     * ➕ POST /ui/cards
     * Обрабатывает добавление новой карточки через HTML-форму (модалку).
     *
     * @ModelAttribute("card") — связывает поля формы с объектом Card.
     * @Valid — включает проверку аннотаций валидации (например, @NotBlank).
     * BindingResult — содержит информацию об ошибках (если поля не прошли проверку).
     */
    @PostMapping("/cards")
    public String create(@ModelAttribute("card") @Valid Card card,
                         BindingResult binding) {

        // Если валидация не прошла — остаёмся на той же странице и показываем ошибки
        if (binding.hasErrors()) {
            return "cards";
        }

        // Сохраняем карточку в базу данных
        repo.save(card);

        // После успешного добавления — возвращаемся на список карточек
        return "redirect:/ui/cards";
    }

    /**
     * 🔍 GET /ui/cards/{id}
     * Открывает отдельную страницу карточки по её id.
     * Если карточка не найдена — выбрасывается ошибка 404 (NOT_FOUND).
     *
     * model.addAttribute("card", c) — передаёт карточку в шаблон card-view.html
     */
    @GetMapping("/cards/{id}")
    public String view(@PathVariable Long id, Model model) {
        Card c = repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Card %d not found".formatted(id))
        );
        model.addAttribute("card", c);
        return "card-view"; // шаблон templates/card-view.html
    }

    /**
     * ❌ POST /ui/cards/{id}/delete
     * Удаляет карточку по ID и возвращает на список карточек.
     * Если карточки с таким id нет — выбрасывает ошибку 404.
     */
    @PostMapping("/cards/{id}/delete")
    public String delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Card %d not found".formatted(id));
        }
        repo.deleteById(id);
        return "redirect:/ui/cards";
    }
}