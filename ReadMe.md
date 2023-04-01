
# [En] Application description

The ScreenRetainer application is designed to lock the screen on an application chosen by the user.

How it works: the user enters this application and selects another application installed on his device,
application installed on his device and it will be opened. Once the selected app is open,
it will work as usual, but if you try to exit that app,
the screen of the device will be locked.
This can be used when you need to put your phone in the hands of another person,
or if you might fall asleep with the phone in your hands,
to prevent intruders from gaining full access to your device.

# Project structure description

## [CoreAndroid]
This module contains dependencies, basic interfaces for all other project modules.

## [СoreСompose]
This module contains basic dependencies, a description of the topic, basic components for modules
which uses compose. Includes [CoreAndroid]

## [AdminReceiver]
The module that contains the administrator of the device that is used to lock the screen
phone.

## [AppListScreen]
A module with a screen that shows all applications available for fixing the screen.

## [OpenAppChangedTrackerService]
The module in which the service is located to receive information when the application on the screen has changed

## [PackageInfoProvider]
Module for getting information about installed packages on the device.

## [PreferencesStorage]
Module for managing user preferences

## [AdmobManager]
Module containing the ad serving tools.

## [BottomBarScreen]
Module containing widget for integrating other displays with pager and bottom bar

## [Database]
Module that holds the database and the repositories to manage that data

## [PreferencesStorage]
Module containing the instrins for managing user preferences

## [QuickSettingsButtonService]
Module containing the service for the display of the quickSettingsButton

## [SettingsScreen]
Module with the application settings screen




# [Ru] Описание приложения

Приложение ScreenRetainer предназначено для фиксации экрана на выбранном пользователем приложении.

Как это работает: пользователь заходит в это приложение и выбирает другое установленное на его устройстве приложение,
и оно открывается. После того, как выбранное приложение открыто,
оно будет работать как обычно, но если попытаться выйти из этого приложения,
то экран устройства будет заблокирован.
Это можно использовать, когда вам необходимо дать телефон в руки другому человеку,
или если вы можете заснуть с телефоном в руках,
чтобы предотвратить злоумышленникам получение полного доступа к устройству.

# Описание структуры проекта

## [CoreAndroid]
В данном модуле находятся зависимости, базовые интерфейсы для всех других модулей проекта.

## [CoreCompose]
В данном модуле находятся базовые зависимости, описание темы, базовые компоненты для модулей
в котором используется compose. Включает в себя [CoreAndroid]

## [AdminReceiver]
Модуль в котором находтся администратор устройства который используется для блокировки экрана 
телефона.

## [AppListScreen]
Модуль с экраном на котором показываются всё приложения доступные для фиксации экрана.

## [OpenAppChangedTrackerService]
Модуль в котором находится service для получении информации когда приложение на экране изменилось

## [PackageInfoProvider]
Модуль для получении информации о уставноленных пакетах на устройтве.

## [PreferencesStorage]
Модуль для управления пользовательскими предпочениями

## [AdmobManager]
Модуль в котором находтся инструменты для показа рекламы.

## [BottomBarScreen]
Модуль в котором находтся виджет для обьеденения других экраном с помощью pager и bottom bar

## [Database]
Модуль в котором находтся база данных и репозитории для управления этими данными

## [QuickSettingsButtonService]
Модуль в котором находтся service для показа кнопки быстрых настроек

## [SettingsScreen]
Модуль с экраном настроек приложения

