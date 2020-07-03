# Pop Movies

## Project Overview
Most of us can relate to kicking back on the couch and enjoying a movieDetail with friends and family.
This app allow users to discover the most popular movies playing. 

![Screenshot 1](screenshot-1.png) 
![Screenshot 2](screenshot-2.png)
![Screenshot 3](screenshot-3.png)

## This app does:

* Present the user with a grid arrangement of movieDetail posters upon launch.
* Allow your user to change sort order via a setting:
* The sort order can be by most popular or by highest-rated
* Allow the user to tap on a movieDetail poster and transition to a details screen with additional information such as:
	* original title
	* movieDetail poster image thumbnail
	* A plot synopsis (called overview in the api)
	* user rating (called vote_average in the api)
	* release date

## How to build
This app utilises the following API's: 

* [The Movie DB] (https://developers.themoviedb.org/3/getting-started)
* [Youtube Data v3](https://developers.google.com/youtube/v3/)

Create the API keys in both services and put them in your local **gradle.properties** file:

Windows: C:\Users\\**YOUR-USERNAME**\\.gradle\gradle.properties

Mac: Users/**YOUR-USERNAME**/.gradle/gradle.properties

	...
	MovieDBApiKey="YOUR-KEY-HERE"
	DeveloperConsoleApiKey="YOUR-KEY-HERE"
