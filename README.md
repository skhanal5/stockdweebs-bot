# stockdweebs-bot

A simple and convenient Discord bot made with JDA, Twitter4J, and MongoDB to alert and notify users for any activity associated with the official StockDweebs account.

## Disclaimer

I am not associated with or sponsored by the StockDweebs brand in any way, shape, or form.

## Command-list/Features

This bot currently supports the commands listed below:

* !setup - contains information to see how the bot should be configured
* !twitter - posts a link to the StockDweebs twitter account
* !youtube - posts a link to the StockDweebs youtube channel
* !stockpicks - posts a link to the dropbox page containing this week's stockpicks
* !watchlist - posts a link to the dropbox page containing this week's watchlist
* !premium - contains information regarding premium services 
* !commands - showcases a list of all of the supported commands
* !invite - posts invite link that can be used to add this bot into other discord servers
* !setchannel* (channel name) -restricts this bot to one text channel in order to reduce spam
* !alerts* (on/off) - posts a real-time stream of twitter posts from the StockDweebs twitter (may be slightly delayed depending on traffic)

These commands are all text-based and can be inputted in a text channel in a discord server after, the bot has been configured with the proper settings. The bot will respond in the designated text-channel as specified by the server admin.

**Warning:** Any commands marked with an asterisk (*) are restricted for Discord users that have the "administrator" permission. Those features are mainly for configuring the bot and should not be allowed for widespread use in a server from others outside the server admin.

## Built-With
* [JDA](https://github.com/DV8FromTheWorld/JDA) - Utilized wrapper to directly have access to Discord API
* [Twitter4J](http://twitter4j.org/en/) - Used for Twitter integration into the Discord bot
* [MongoDB](https://www.mongodb.com/) - Used cloud based database to store discord server information

## Acknowledgements
* [Xemor](https://www.youtube.com/watch?v=MEnYZriBQ9k&list=PLI4ARPbf7eAWlsIBlZxCJSBijMxdDkfdo) - Utilized guides for project initialization and offered assistance with JDA library

## Author
* **Subodh Khanal** 

## License
This project is licensed under the APACHE License, Version 2.0 - see the [LICENSE] file in the repository for more information
