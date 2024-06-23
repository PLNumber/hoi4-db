package kr.ac.kumoh.ce.s20180665.hoi4_db
/*20180665 안재모*/
/*사령관 정보 조회용*/
data class CmdInfoDTO(
    /*국가 정보*/
    val tag: String,
    val countryName: String,
    val image: String?,
    /*사령관 정보*/
    val commanderName: String,
    val atk: Int,
    val def: Int,
    val pln: Int,
    val log: Int
)
/*지도자 조회용*/
data class LdInfoDTO(
    /*국가정보*/
    val tag: String,
    val countryName: String,
    val countryImage: String,
    /*이념정보*/
    val ideologyName: String,
    /*지도자 정보*/
    val leaderName: String,
    val portrait: String?
)
