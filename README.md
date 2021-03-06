# stockdweebs-bot

![language](https://img.shields.io/github/languages/top/skhanal5/stockdweebs-bot)
![license](https://img.shields.io/github/license/skhanal5/stockdweebs-bot)
![commit](https://img.shields.io/github/last-commit/skhanal5/stockdweebs-bot)
![issues](https://img.shields.io/github/issues/skhanal5/stockdweebs-bot)
![stars](https://img.shields.io/github/stars/skhanal5/stockdweebs-bot?style=social)

A simple and convenient Discord bot made with JDA, Twitter4J, and MongoDB to alert and notify users for any activity associated with the official StockDweebs account.

## Disclaimer

I am not associated with or sponsored by the StockDweebs brand in any way, shape, or form.

## Setting up the Bot in Discord

In order to add this discord bot to your discord server, use this [invite link](https://discord.com/oauth2/authorize?client_id=780691100964356146&permissions=93232&scope=bot). Once it joins the server, the bot should prompt the user with this message:

![image](https://user-images.githubusercontent.com/74752121/103317153-a7bf9600-49f8-11eb-958d-a1107d164c3d.png)

In the event this message does not automatically show up, type !setup in any channel to see this message. From there, you need to designate a text channel for the bot to post alerts and messages on by typing the command !setchannel [server name] into any text channel like so:

[![Image from Gyazo](https://i.gyazo.com/c1ec78c9bc933e3c87da5622576339c0.gif)](https://gyazo.com/c1ec78c9bc933e3c87da5622576339c0)

As shown, the bot will respond immediately with a message saying that it will only post messages on the channel you specified. From there, you can use the rest of the commands listed below.

## Command-list/Features

This bot currently supports the commands listed below:

* !setup - contains information to see how the bot should be configured
* !twitter - posts a link to the StockDweebs twitter account
* !youtube - posts a link to the StockDweebs youtube channel
* !help - posts clarification on how to use the bot
* !stockpicks - posts a link to the page on StockDweebs' website containing this week's stock picks
* !pastpicks - posts a link to the page on StockDweebs' website containing previous weeks stock picks.
* !premium - contains information regarding premium services 
* !commands - showcases a list of all of the supported commands
* !invite - posts invite link that can be used to add this bot into other discord servers
* !setchannel* (channel name) -restricts this bot to one text channel in order to reduce spam
* !alerts* (on/off) - posts a real-time stream of twitter posts from the StockDweebs twitter (may be slightly delayed depending on traffic)

These commands are all text-based and can be inputted in a text channel in a discord server once the bot has been configured with the proper settings. The bot will respond in the designated text-channel as specified by the server admin.

**Warning:** Any commands marked with an asterisk (*) are restricted for Discord users that have the "administrator" permission. Those features are mainly for configuring the bot and should not be allowed for widespread use in a server from others outside the server admin.

## Bug Reports / Feature Requests

If you encounter any problems with the Discord bot, please make an issue with an appropriate title, label, and a detailed response [here](https://github.com/skhanal5/stockdweebs-bot/issues). Similarly, if you have any requests for additional features to be added onto this bot, please submit an issue with the label "feature-request" [here](https://github.com/skhanal5/stockdweebs-bot/issues) describing the feature you would like added. I will do my best to look over these reports and requests in my spare time.

## Editing/Contributing to the Project

I am currently working on adding comments and making the code more comprehensive for all users. In the future, I will add a page to the [wiki](https://github.com/skhanal5/stockdweebs-bot/wiki) that will thoroughly explain how to use this repository as intended.

## Built With
* [JDA](https://github.com/DV8FromTheWorld/JDA) - Utilized wrapper to directly have access to Discord API
* [Twitter4J](http://twitter4j.org/en/) - Used for Twitter integration into the Discord bot
* [MongoDB](https://www.mongodb.com/) - Used cloud based database to store discord server information
* [Heroku](https://dashboard.heroku.com/apps) - Used to deploy bot online 24/7

## Acknowledgements
* [Xemor](https://www.youtube.com/watch?v=MEnYZriBQ9k&list=PLI4ARPbf7eAWlsIBlZxCJSBijMxdDkfdo) - Utilized guides for project initialization and offered assistance with JDA library

## Author
* **Subodh Khanal** 

## License
This project is licensed under the APACHE License, Version 2.0 - see the [LICENSE] file in the repository for more information
