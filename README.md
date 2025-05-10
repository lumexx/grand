Алгоритм Запуска:
1. Поднимаем бд и редис: docker-compose up -d
2. Прогоняем тесты mvn 
3. Запускаем API
4. Переходим по ***/swagger-ui/index.html
5. /login (username: test@gmail.com, password: admin)
6. Вставить полученный токен в Authorize сваггера
7. Profit!

Redis использовал в Scheduled таске для хранения данных связанных с начальным депозитом, запрос к данным закэшировал
Для взаимодействия с API используется UUID (BaseEntityListener.onPersist)
