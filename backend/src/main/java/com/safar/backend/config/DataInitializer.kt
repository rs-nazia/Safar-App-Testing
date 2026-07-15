package com.safar.backend.config

import com.safar.backend.model.Destination
import com.safar.backend.model.DestinationType
import com.safar.backend.repository.DestinationRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DataInitializer {

    // Image Constants
    private val IMG_SRI_LANKA = "https://images.unsplash.com/photo-1588258524675-55d656396b8a?q=80&w=2067&auto=format&fit=crop"
    private val IMG_EGYPT = "https://images.unsplash.com/photo-1539650116455-d2b585317d33?q=80&w=2070&auto=format&fit=crop"
    private val IMG_MALDIVES = "https://images.unsplash.com/photo-1514282401047-d79a71a590e8?q=80&w=2065&auto=format&fit=crop"
    private val IMG_NEPAL = "https://images.unsplash.com/photo-1544735716-392fe2489ffa?q=80&w=2071&auto=format&fit=crop"

    @Bean
    fun initData(
        destRepository: DestinationRepository,
        agencyRepository: com.safar.backend.repository.AgencyRepository,
        userRepository: com.safar.backend.repository.UserRepository
    ) = CommandLineRunner {
        if (destRepository.count() == 0L) {
            val destinations = listOf(
                // Trending
                Destination(
                    title = "Cairo, Egypt",
                    location = "Egypt",
                    description = "A Journey of Faith and Timeless Beauty. Visit the Pyramids and the Nile.",
                    duration = "5 Days / 4 Nights",
                    imageUrl = IMG_EGYPT,
                    type = DestinationType.TRENDING
                ),
                Destination(
                    title = "Maldives",
                    location = "Maldives",
                    description = "The Sunny Side of Life. Crystal clear waters and luxury resorts.",
                    duration = "4 Days / 3 Nights",
                    imageUrl = IMG_MALDIVES,
                    type = DestinationType.TRENDING
                ),
                Destination(
                    title = "Kathmandu",
                    location = "Nepal",
                    description = "A Poetic Passage Through Nepal. Mountains and Temples.",
                    duration = "5 Days / 4 Nights",
                    imageUrl = IMG_NEPAL,
                    type = DestinationType.TRENDING
                ),
                
                // Community
                Destination(
                    title = "Bhanugach",
                    location = "Sylhet Division",
                    description = "Noiseless Home-stay: A Tranquil Escape in the heart of nature.",
                    imageUrl = IMG_SRI_LANKA, 
                    type = DestinationType.COMMUNITY
                )
            )
            destRepository.saveAll(destinations)
        }

        if (agencyRepository.count() == 0L) {
            val agencies = listOf(
                com.safar.backend.model.Agency(
                    name = "Sylhet Travelers Ltd",
                    location = "Sylhet, Bangladesh",
                    experience = "8 years",
                    services = "Domestic & International Tours",
                    countries = "Bangladesh, UAE, Saudi",
                    rating = "4.6",
                    verified = true,
                    logo = "https://images.unsplash.com/photo-1543610892-0b1f7e6d7ac1?q=80&w=1856",
                    price = "৳5,000",
                    regId = "TRV-SYL-001",
                    address = "Zindabazar, Sylhet",
                    phone = "+8801712345678",
                    email = "contact@sylhettravelers.com"
                ),
                com.safar.backend.model.Agency(
                    name = "Global Wings Travel",
                    location = "Dhaka, Bangladesh",
                    experience = "12 years",
                    services = "Visa & Air Tickets",
                    countries = "USA, Canada, UK",
                    rating = "4.4",
                    verified = true,
                    logo = "https://images.unsplash.com/photo-1599305090598-fe179d501c27?q=80&w=2070",
                    price = "৳12,000",
                    regId = "GW-DHK-092",
                    address = "Banani, Dhaka",
                    phone = "+8801812345678",
                    email = "support@globalwings.com"
                )
            )
            agencyRepository.saveAll(agencies)
        }

        if (userRepository.count() == 0L) {
            userRepository.save(com.safar.backend.model.UserRecord(
                name = "Milad",
                email = "milad@example.com",
                isAdmin = true
            ))
        }
    }
}
