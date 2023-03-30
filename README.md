# BinInfo - тестовое задание конкурса проекта ШИФТ ЦФТ.
## Описание задачи
___

Реализовать Android-приложение со следующими функциями:
1. Пользователь вводит [BIN](https://www.banki.ru/wikibank/bankovskiy_identifikatsionnyiy_nomer/) банковской карты и видит всю доступную информацию о нём,
загруженную с https://binlist.net/
1. История предыдущих запросов выводится списком
2. История предыдущих запросов не теряется при перезапуске приложения
3. Нажатие на URL банка, телефон банка, координаты страны отправляет пользователя в
приложение, которое может обработать эти данные (браузер, звонилка, карты)
## Описание приложения
___
### Главный экран
На главном экране предсавлен список истории запросов и кнопка для создания нового запроса.

![](https://github.com/markkuzi/BinInfo/blob/master/media/screenshot-main.png)

Список отсортирован по дате запроса, при нажатии на элемент списка отобразит детальную информацию о нем, при свайпе влево запрос будет удален из списка, при свайпе вправо запрос будет обновлен и отобразиться информация об этом элементе. При нажатии на кнопку для создания нового запроса, отобразиться диалоговое окно, куда можно ввести номер [BIN](https://www.banki.ru/wikibank/bankovskiy_identifikatsionnyiy_nomer/) и при нажатии на кнопку SEARCH BIN INFO создасться новый запрос и отобразиться инфомрация о нем.

![](https://github.com/markkuzi/BinInfo/blob/master/media/screenshot-new.png)

### Экран детальной информации
Экран имеет 3 статуса:
* SUCCESS - запрос успешен, на экране отобразиться вся полученная информация о карте, при нажатии на номер телефона будет сделан звонок по номеру, при нажатии на URL банка будет открыт браузер с данным URL.

![](https://github.com/markkuzi/BinInfo/blob/master/media/screenshot-success.png)

* NO_RESULT - запрос успешен, но по данному [BIN](https://www.banki.ru/wikibank/bankovskiy_identifikatsionnyiy_nomer/) никакой информации нет.

![](https://github.com/markkuzi/BinInfo/blob/master/media/screenshot-no-result.png)

* ERROR - ошибка при запросе на сервер, возможно отсутсвие интернета или ошибка на самом сервере.

![](https://github.com/markkuzi/BinInfo/blob/master/media/screenshot-error.png)
  
На экране всех трех статусов имеются кнопки BACK и DELETE. Кнопка BACK возвращает нас на главный экран, кнопка DELETE удаляет данный запрос из истории запросов.

На экрана при статусе NO_RESULT и ERROR так же есть кнопка RETRY, которая позволяет сделать повторный запрос.



## Стек
___
![Language](https://img.shields.io/badge/Language-Kotlin-green)
![Architecture](https://img.shields.io/badge/Architecture-Clean%20Architecture-green)
![Architecture Pattern](https://img.shields.io/badge/Architecture%20Pattern-MVVM-green)
![HTTP client](https://img.shields.io/badge/HTTP%20client-Retrofit2-green)
![JSON Converter](https://img.shields.io/badge/JSON%20Converter-Moshi-green)
![Local Database](https://img.shields.io/badge/Local%20Database-Room-green)
![DI](https://img.shields.io/badge/Dependency%20Injection-Koin-green)
![Asynchrony](https://img.shields.io/badge/Asynchrony-Coroutine-green)
![Navigation](https://img.shields.io/badge/Navigation-Jetpack's%20Navigation%20component-green)
