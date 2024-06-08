package kr.ac.kumoh.ce.s20180665.hoi4_db

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Country(
    @Id
    var tag: String,
    var name: String,
    var civilFactory: Int,
    var miliFactory: Int,
    var image: String?
)
