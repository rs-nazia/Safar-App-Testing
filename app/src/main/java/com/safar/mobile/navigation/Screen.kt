package com.safar.mobile.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object Signup : Screen("signup")
    object Home : Screen("home")
    object Flights : Screen("flights")
    object Hotels : Screen("hotels")
    object Tours : Screen("tours")
    object Profile : Screen("profile")
    object DestinationDetails : Screen("details/{destinationId}") {
        fun createRoute(destinationId: Int) = "details/$destinationId"
    }
    object HostelDetails : Screen("hostel_details/{hostelId}") {
        fun createRoute(hostelId: String) = "hostel_details/$hostelId"
    }
    object PaymentMethods : Screen("payment_methods")
    object Agencies : Screen("agencies")
    object AgencyProfile : Screen("agency_profile/{agencyId}") {
        fun createRoute(agencyId: String) = "agency_profile/$agencyId"
    }
    object AgencyRegistration : Screen("agency_reg")
    object Help : Screen("help")
}
