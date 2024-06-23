package kr.ac.kumoh.ce.s20180665.hoi4_db
/*20180665 안재모*/

import jakarta.persistence.*
import java.io.Serializable

/*국가 테이블 구현*/
@Entity
data class Country(
    @Id
    var tag: String,    //프라이머리 키
    var name: String,
    var civilFactory: Int,
    var miliFactory: Int,
    var image: String?
)

/*복합 프라이머리키 */
@Embeddable
data class LeaderId(
    var countryTag: String,
    var ideologyId: Int
) : Serializable

/*지도자 테이블 구현*/
@Entity
data class Leader(
    @EmbeddedId
    var leaderId: LeaderId, //복합 키

    var name: String,
    var portrait: String?,

    @OneToOne   //일대일 관계
    @MapsId("countryTag")
    @JoinColumn(name="country_tag")
    var country: Country,

    @ManyToOne  //다대일 관계
    @MapsId("ideologyId")
    @JoinColumn(name = "ideology_id")
    var ideology: Ideology
)

/*사령관 테이블 구현*/
@Entity
data class Commander(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동증가
    var id: Int,    //프라이머리 키
    var name: String,
    var atk: Int,
    var def: Int,
    var pln: Int,
    var log: Int,

    @ManyToOne  //다대일
    @JoinColumn(name= "country_tag")
    var country: Country
)

/*이념 테이블 구현*/
@Entity
data class Ideology(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,    //프라이머리 키
    var name: String
)