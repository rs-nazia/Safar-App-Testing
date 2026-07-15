package com.safar.mobile.data

import com.safar.mobile.model.Destination
import com.safar.mobile.model.DestinationType

object MockData {
    val internationalTourPackages = listOf(
        Destination(
            id = 201,
            title = "USA Tour",
            location = "New York",
            description = "Experience the best of East Coast USA with a luxury family tour.",
            duration = "7 Days/ 6 Nights",
            imageUrl = "https://images.unsplash.com/photo-1485738422979-f5c462d49f74?q=80&w=2070",
            price = "৳3,50,000",
            type = DestinationType.INTERNATIONAL,
            category = "Luxury | Family",
            hotelName = "Hilton Garden Inn",
            hotelGrade = "4 Star",
            itinerary = mapOf(
                "Day 1-2" to listOf("Statue of Liberty visit", "Times Square evening tour"),
                "Day 3" to listOf("Washington DC White House & Capitol Hill Tour"),
                "Day 4" to listOf("Niagara Falls boat tour"),
                "Day 5-6" to listOf("Shopping & NYC City Tour"),
                "Day 7" to listOf("Return Flight")
            ),
            inclusions = listOf("Visa assistance", "Hotel stay", "Coach transport", "Guide", "Breakfast"),
            exclusions = listOf("Personal expenses", "Dinner")
        ),
        Destination(
            id = 202,
            title = "Canada Tour",
            location = "Toronto",
            description = "Explore the vast natural beauty and modern cities of Canada.",
            duration = "6 days/ 5 Nights",
            imageUrl = "file:///android_asset/images/canada_tour.jpg",
            price = "৳3,00,000",
            type = DestinationType.INTERNATIONAL,
            category = "Nature | Family",
            hotelName = "Sheraton Toronto",
            hotelGrade = "4 Star",
            itinerary = mapOf(
                "Day 1" to listOf("Arrival in Toronto", "CN Tower Visit"),
                "Day 2" to listOf("Niagara Falls (Canadian Side)"),
                "Day 3" to listOf("Flight to Vancouver"),
                "Day 4" to listOf("Banff National Park Tour"),
                "Day 5" to listOf("City Sightseeing"),
                "Day 6" to listOf("Return")
            ),
            inclusions = listOf("Airport Transfer", "Daily Breakfast", "Sightseeing"),
            exclusions = listOf("Visa Fees")
        ),
        Destination(
            id = 203,
            title = "Umrah & Religious Tour",
            location = "Makkah, Madinah, Jeddah",
            description = "Perform Umrah with peace of mind in high-quality accommodations.",
            duration = "10 Days",
            imageUrl = "https://images.unsplash.com/photo-1565011523534-747a8601f10a?q=80&w=1974",
            price = "৳2,20,000",
            type = DestinationType.INTERNATIONAL,
            category = "Religious",
            hotelName = "Anwar Al Madinah Hotel",
            hotelGrade = "4 Star",
            itinerary = mapOf(
                "Day 1" to listOf("Arrival in Jeddah", "Transfer to Makkah"),
                "Day 2-5" to listOf("Umrah Rituals", "Ibadah in Haram"),
                "Day 6" to listOf("Transfer to Madinah"),
                "Day 7-9" to listOf("Masjid Nabawi visit", "Ziyarat Tours"),
                "Day 10" to listOf("Jeddah Tour & Return")
            ),
            inclusions = listOf("Umrah Visa", "AC Bus transport", "Guide", "Breakfast & Dinner"),
            exclusions = listOf("Qurbani personal expenses")
        ),
        Destination(
            id = 204,
            title = "Dubai Luxury City Tour",
            location = "Dubai & Abu Dhabi",
            description = "A premium desert experience with luxury city landmarks.",
            duration = "5 Days / 4 Nights",
            imageUrl = "https://images.unsplash.com/photo-1512453979798-5ea266f8880c?q=80&w=2070",
            price = "৳1,50,000",
            type = DestinationType.INTERNATIONAL,
            category = "Luxury | Honeymoon",
            hotelName = "Citymax Hotel",
            hotelGrade = "3 Star",
            itinerary = mapOf(
                "Day 1" to listOf("Arrival", "Dhow Cruise Dinner"),
                "Day 2" to listOf("Burj Khalifa & Dubai Mall"),
                "Day 3" to listOf("Desert Safari with BBQ Dinner"),
                "Day 4" to listOf("Abu Dhabi City Tour"),
                "Day 5" to listOf("Airport Drop-off")
            ),
            inclusions = listOf("UAE Visa", "Airport Pickup", "Breakfast & 1 Dinner"),
            exclusions = listOf("Personal shopping")
        )
    )



    val sylhetTourPackages = listOf(
        Destination(
            id = 101,
            title = "Sylhet Nature Escape",
            location = "Sylhet, Bangladesh",
            description = "Experience the lush green tea gardens and shallow crystal clear river.",
            duration = "3 Days / 2 Nights",
            imageUrl = "file:///android_asset/images/sylhet_nature.jpg",
            price = "৳12,500",
            type = DestinationType.SYLHET_PACKAGE,
            category = "Nature | Family",
            hotelName = "Hotel Garden Inn",
            hotelGrade = "3 Star",
            itinerary = mapOf(
                "Day 1" to listOf("Arrival in Sylhet", "Tea Garden visit", "Sunset at Jaflong"),
                "Day 2" to listOf("Boat ride in Ratargul", "Lalakhal river cruise"),
                "Day 3" to listOf("Local shopping", "Return")
            ),
            inclusions = listOf("Hotel stay", "Transport (AC Microbus)", "Guide", "2 Breakfast", "2 Lunch"),
            exclusions = listOf("Personal expenses", "Dinner")
        ),

        Destination(
            id = 103,
            title = "Sylhet Religious Tour",
            location = "Sylhet City",
            description = "Visit the holy shrines of Hazrat Shah Jalal (R) and Shah Paran (R).",
            duration = "2 Days / 1 Night",
            imageUrl = "https://images.unsplash.com/photo-1542314831-068cd1dbfeeb?auto=format&fit=crop&w=800&q=60", // Better religious architecture
            price = "৳6,500",
            type = DestinationType.SYLHET_PACKAGE,
            category = "Religious | Budget",
            hotelName = "Hotel Noorjahan Grand",
            hotelGrade = "3 Star",
            itinerary = mapOf(
                "Day 1" to listOf("Arrival", "Hazrat Shah Jalal (R) Mazar"),
                "Day 2" to listOf("Hazrat Shah Paran (R) Mazar", "Khadimnagar", "Return")
            ),
            inclusions = listOf("Hotel stay", "Transport (Non-AC Bus)", "Guide"),
            exclusions = listOf("Meals")
        ),
        Destination(
            id = 104,
            title = "Adventure Sylhet Trip",
            location = "Bisnakandi & Ratargul",
            description = "For the thrill-seekers: Hiking, Boat Riding and more.",
            duration = "3 Days / 2 Nights",
            imageUrl = "https://images.unsplash.com/photo-1501785888041-af3ef285b470?q=80&w=2070",
            price = "৳15,000",
            type = DestinationType.SYLHET_PACKAGE,
            category = "Adventure | Youth",
            hotelName = "Rose View Hotel",
            hotelGrade = "4 Star",
            itinerary = mapOf(
                "Day 1" to listOf("Arrival", "Bisnakandi Boat Ride"),
                "Day 2" to listOf("Panthumai Waterfall", "Ratargul Swamp Forest"),
                "Day 3" to listOf("Hiking", "Return")
            ),
            inclusions = listOf("Hotel stay", "Activities", "Guide"),
            exclusions = listOf("Personal expenses")
        ),
        Destination(
            id = 105,
            title = "Weekend Tour",
            location = "Jaflong & Tea Gardens",
            description = "A quick budget-friendly refreshment tour.",
            duration = "2 Days / 1 Night",
            imageUrl = "https://images.unsplash.com/photo-1534067783941-51c9c23ecefd?q=80&w=1974", // Corrected image
            price = "৳4,999",
            type = DestinationType.SYLHET_PACKAGE,
            category = "Budget | Short Trip",
            hotelName = "Hotel Mira Garden",
            hotelGrade = "2 Star",
            itinerary = mapOf(
                "Day 1" to listOf("Arrival", "Jaflong"),
                "Day 2" to listOf("Tea Gardens", "Local Market", "Return")
            ),
            inclusions = listOf("Hotel stay", "Transport"),
            exclusions = listOf("Food", "Personal expenses")
        )
    )
    
    // Combine for Trending to show something on Home
    val trendingDestinations = listOf(
        internationalTourPackages[0], // USA
        sylhetTourPackages[0],        // Sylhet Nature
        internationalTourPackages[3], // Dubai
        sylhetTourPackages[3]         // Weekend Tour
    )

    val hotels = listOf(
        com.safar.mobile.model.Hotel(
            id = "1",
            name = "City Backpackers Hostel",
            location = "Dubai, UAE",
            pricePerNight = "৳2,500",
            rating = 4.2f,
            imageUrl = "file:///android_asset/images/city_backpackers.jpg",
            facilities = listOf("Free WiFi", "AC", "Locker", "24/7 Security"),
            grade = "Hostel"
        ),
        com.safar.mobile.model.Hotel(
            id = "h1",
            name = "Holiday Inn",
            location = "New York, USA",
            pricePerNight = "৳15,000",
            rating = 4.5f,
            imageUrl = "https://images.unsplash.com/photo-1445019980597-93fa8acb246c?auto=format&fit=crop&w=800&q=60",
            facilities = listOf("WiFi", "AC", "Breakfast", "Pool"),
            grade = "4 Star"
        ),
        com.safar.mobile.model.Hotel(
            id = "h2",
            name = "Sheraton Toronto",
            location = "Toronto, Canada",
            pricePerNight = "৳18,000",
            rating = 4.6f,
            imageUrl = "https://images.unsplash.com/photo-1566073771259-6a8506099945?auto=format&fit=crop&w=800&q=60",
            facilities = listOf("WiFi", "AC", "Gym", "Spa"),
            grade = "4 Star"
        ),
        com.safar.mobile.model.Hotel(
            id = "h3",
            name = "Hotel Dar Al Eiman",
            location = "Makkah, Saudi Arabia",
            pricePerNight = "৳12,000",
            rating = 4.8f,
            imageUrl = "https://images.unsplash.com/photo-1564501049412-61c2a3083791?auto=format&fit=crop&w=800&q=60",
            facilities = listOf("Near Haram", "WiFi", "AC", "Restaurant"),
            grade = "4 Star"
        ),
        com.safar.mobile.model.Hotel(
            id = "h4",
            name = "Anwar Al Madinah Hotel",
            location = "Madinah, Saudi Arabia",
            pricePerNight = "৳10,000",
            rating = 4.7f,
            imageUrl = "https://images.unsplash.com/photo-1591604129939-f1efa4d9f7fa?auto=format&fit=crop&w=800&q=60",
            facilities = listOf("WiFi", "AC", "Ziyarat Service"),
            grade = "4 Star"
        ),
        com.safar.mobile.model.Hotel(
            id = "h5",
            name = "Citymax Hotel",
            location = "Dubai, UAE",
            pricePerNight = "৳8,000",
            rating = 4.0f,
            imageUrl = "file:///android_asset/images/citymax_hotel.jpg",
            facilities = listOf("WiFi", "AC", "Shuttle Service"),
            grade = "3 Star"
        ),
        com.safar.mobile.model.Hotel(
            id = "h6",
            name = "Atlantis The Palm",
            location = "Dubai, UAE",
            pricePerNight = "৳45,000",
            rating = 5.0f,
            imageUrl = "https://images.unsplash.com/photo-1542314831-068cd1dbfeeb?q=80&w=2070",
            facilities = listOf("Water Park", "Luxury Spa", "Private Beach"),
            grade = "5 Star"
        )
    )

    val sampleFlights = listOf(
        com.safar.mobile.model.Flight(
            id = "1",
            airline = "British Airways",
            airlineLogoUrl = "", // In real app, load from URL
            from = "New York (JFK)",
            to = "London (LHR)",
            departureTime = "10:00",
            arrivalTime = "18:00",
            duration = "8h",
            price = 450.0
        ),
        com.safar.mobile.model.Flight(
            id = "2",
            airline = "US Airways",
            airlineLogoUrl = "",
            from = "Dhaka (DAC)",
            to = "New York (JFK)",
            departureTime = "02:00",
            arrivalTime = "22:00",
            duration = "20h",
            price = 1200.0
        ),
        com.safar.mobile.model.Flight(
            id = "3",
            airline = "Emirates",
            airlineLogoUrl = "",
            from = "Dhaka (DAC)",
            to = "Dubai (DXB)",
            departureTime = "19:00",
            arrivalTime = "23:00",
            duration = "4h",
            price = 450.0
        )
    )
    val samplePaymentMethods = listOf(
        mapOf("type" to "Visa", "number" to "**** 4242", "expiry" to "12/26"),
        mapOf("type" to "MasterCard", "number" to "**** 5555", "expiry" to "08/25"),
        mapOf("type" to "bKash", "number" to "017****1234", "expiry" to "Mobile Wallet")
    )

    val sampleAgencies = listOf(
        mapOf(
            "id" to "1",
            "name" to "Sylhet Travelers Ltd",
            "location" to "Sylhet, Bangladesh",
            "experience" to "8 years",
            "services" to "Domestic & International Tours",
            "countries" to "Bangladesh, UAE, Saudi",
            "rating" to "4.6",
            "verified" to "true",
            "logo" to "https://images.unsplash.com/photo-1543610892-0b1f7e6d7ac1?auto=format&fit=crop&w=800&q=60",
            "featureImage" to "file:///android_asset/images/sylhet_travelers_ltd.jpg",
            "price" to "৳5,000",
            "regId" to "TRV-SYL-001",
            "address" to "Zindabazar, Sylhet",
            "phone" to "+8801712345678",
            "email" to "contact@sylhettravelers.com"
        ),
        mapOf(
            "id" to "2",
            "name" to "Global Wings Travel",
            "location" to "Dhaka, Bangladesh",
            "experience" to "12 years",
            "services" to "Visa & Air Tickets",
            "countries" to "USA, Canada, UK",
            "rating" to "4.4",
            "verified" to "true",
            "logo" to "https://images.unsplash.com/photo-1599305090598-fe179d501c27?q=80&w=2070",
            "featureImage" to "https://images.unsplash.com/photo-1449034446853-66c86144b0ad?q=80&w=2070",
            "price" to "৳12,000",
            "regId" to "GW-DHK-092",
            "address" to "Banani, Dhaka",
            "phone" to "+8801812345678",
            "email" to "support@globalwings.com"
        ),
        mapOf(
            "id" to "3",
            "name" to "Holy Journey Tours",
            "location" to "Dhaka, Bangladesh",
            "experience" to "5 years",
            "services" to "Umrah & Hajj",
            "countries" to "Saudi Arabia",
            "rating" to "4.8",
            "verified" to "true",
            "logo" to "https://images.unsplash.com/photo-1425421598808-4a21c5d7032f?q=80&w=2069",
            "featureImage" to "https://images.unsplash.com/photo-1542810634-71277d95dcbb?q=80&w=2070",
            "price" to "৳1,80,000",
            "regId" to "HJ-DHK-555",
            "address" to "Uttara, Dhaka",
            "phone" to "+8801912345678",
            "email" to "info@holyjourney.com"
        )
    )
}
