package com.nurlanamirzayeva.gamejet.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.nurlanamirzayeva.gamejet.ui.activities.mainpage.Screens

enum class BottomNavItems(val route: String, val icon: ImageVector, val title: String,val color:Color) {
    Home(route=Screens.MainPage, icon=Icons.Rounded.Home, title="Home", color =Color(0xFFADFF64)),
    Favorite(route=Screens.Favorite,icon=Icons.Rounded.Favorite,title="Favorite",color= Color(0xFFFA6FFF)) ,
    Profile (route=Screens.Profile, icon=Icons.Rounded.Person, title="Profile",color=Color(0xFFFFA574))

}