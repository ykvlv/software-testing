# Лабораторная №4 - по ТПО
## Задание
С помощью программного пакета [Apache JMeter](http://jmeter.apache.org/) провести нагрузочное и стресс-тестирование веб-приложения в соответствии с вариантом задания.

В ходе нагрузочного тестирования необходимо протестировать 3 конфигурации аппаратного обеспечения и выбрать среди них наиболее дешёвую, удовлетворяющую требованиям по максимальному времени отклика приложения при заданной нагрузке (в соответствии с вариантом).

В ходе стресс-тестирования необходимо определить, при какой нагрузке выбранная на предыдущем шаге конфигурация перестаёт удовлетворять требованиями по максимальному времени отклика. Для этого необходимо построить график зависимости времени отклика приложения от нагрузки.

### Параметры тестируемого веб-приложения:
1. URL первой конфигурации ($ 2500) - http://aqua:8080?token=468481842&user=2022905882&conf=1;
2. URL второй конфигурации ($ 3800) - http://aqua:8080?token=468481842&user=2022905882&conf=2;
3. URL третьей конфигурации ($ 6800) - http://aqua:8080?token=468481842&user=2022905882&conf=3;
4. Максимальное количество параллельных пользователей - 7;
5. Средняя нагрузка, формируемая одним пользователем - 40 запр. в мин.;
6. Максимально допустимое время обработки запроса - 730 мс.

## Расположение файлов
+ [Load](./load) `<- Файлы нагрузочного тестирования`
+ [Stress](./stress) `<- Файлы стресс-тестирования`

## Полезная информация
+ [JMeter](http://jmeter.apache.org/)
+ [JMeter Guide](https://www.tutorialspoint.com/jmeter/jmeter_quick_guide.htm)

### Доступ к серверу aqua
После пробрасывания порта, в тестах всё равно нужно использовать `localhost` в качестве url.  

### Генерация HTML-отчета по результатам тестирования
1. Сгенерировать `csv-файл` с результатами тестирования
2. Создать файл конфигурации `user.properties` (может быть пустым)
3. Нажать `Tools` -> `Generate HTML Report`
4. Указать путь для:
    - файл с таблицойжив тестирования
    - файла настроек (`user.properties`)
    - директории, куда будет сохранён отчёт (обязана будь пустой)