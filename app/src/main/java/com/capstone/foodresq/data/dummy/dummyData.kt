package com.capstone.foodresq.data.dummy

import com.capstone.foodresq.R

object dummyData {
    val dummyFood = listOf(
        Food(
            1,
            R.drawable.food1,
            "Pie Buah Vla",
            20000,
            16000,
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            12
        ),
        Food(
            2,
            R.drawable.food2,
            "Steak and Soup",
            75000,
            30000,
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            21
        ),
        Food(
            3,
            R.drawable.food3,
            "Diet Bowl",
            85000,
            62000,
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            5
        ),
        Food(
            4,
            R.drawable.food4,
            "Sushi set 2",
            102000,
            95000,
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            2
        ),
        Food(
            5,
            R.drawable.food5,
            "Sauce Pizza",
            55000,
            35000,
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            17
        )
    )

    val dummyRestaurant = listOf(
        Restaurant(
            1,
            R.drawable.restoran1,
            "Kopi Buka",
            "08:30",
            "21:00",
            "Jl. Margahayu Selatan no.21-A",
            4.5,
            -6.230730,
            106.987146,
            dummyFood
        ),
        Restaurant(
            2,
            R.drawable.restoran2,
            "Kebab Bakar Abiza",
            "17:00",
            "03:00",
            "Jl. H. Yamin no.14",
            4.0,
            -6.232219,
            107.002010,
            dummyFood
        ),
        Restaurant(
            3,
            R.drawable.restoran3,
            "Bakmi GM",
            "08:00",
            "20:00",
            "Jl. Ahmad Yani Boulevard Hijau no.117-B",
            4.3,
            -6.228142,
            107.001977,
            dummyFood
        ),
        Restaurant(
            4,
            R.drawable.restoran4,
            "Waroeng Ngopi Santay",
            "17:30",
            "23:50",
            "Jl. Rangkas Raya Belitung No.13",
            4.0,
            -6.220534,
            107.020084,
            dummyFood
        ),
        Restaurant(
            5,
            R.drawable.restoran5,
            "Bubur Ayam Keroncong",
            "06:00",
            "13:30",
            "Jl. Selamat Raya No.13-B",
            3.5,
            -6.221702,
            107.026273,
            dummyFood
        ),
        Restaurant(
            6,
            R.drawable.restoran6,
            "Gourmets Eats Summarecon",
            "09:00",
            "23:30",
            "Jl. Boulevard Selatan Mall Summarecon Lt.2",
            4.4,
            -6.227974587871831,
            107.00071847935932,
            dummyFood
        )
    )
}