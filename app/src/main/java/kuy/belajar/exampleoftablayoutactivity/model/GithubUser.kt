package kuy.belajar.exampleoftablayoutactivity.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Imam Fahrur Rofi on 29/12/2020.
 */
@Parcelize
data class GithubUser(
    var id: Int = 0,
    var login: String? = null,
    var name: String? = null,
    var avatar: String? = null,
    var company: String? = null,
    var location: String? = null,
    var blog: String? = null,
    var repository: String? = null,
    var follower: Int = 0,
    var following: Int = 0
) : Parcelable
