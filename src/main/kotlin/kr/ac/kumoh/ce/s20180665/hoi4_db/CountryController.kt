package kr.ac.kumoh.ce.s20180665.hoi4_db

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CountryController(val service: CountryService){
    @GetMapping("/")
    fun welcome(): String {
        return "Welcome to HOIKI4"
    }

    @GetMapping("/countries")
    fun countryList(): List<Country> {
        return service.getAllCountries()
    }

    @GetMapping("/countries/{tag}")
    fun getCountry(@PathVariable tag: String): ResponseEntity<Country> {
        val cty = service.findCountryByTag(tag)
        return ResponseEntity.ok(cty)
    }
}