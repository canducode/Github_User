package com.submission.githubuser

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var imgAvatar: ImageView = findViewById(R.id.img_avatar)
        var tvName: TextView = findViewById(R.id.tv_name)
        var tvUsername: TextView = findViewById(R.id.tv_username)
        var btnFollower: Button = findViewById(R.id.btn_follower)
        var btnFollowing: Button = findViewById(R.id.btn_following)
        var tvCompany: TextView = findViewById(R.id.tv_company)
        var tvLocation: TextView = findViewById(R.id.tv_location)
        var tvRepo: TextView = findViewById(R.id.tv_repo)

        val user = intent.getParcelableExtra<Parcelable>(EXTRA_USER) as User

        user.avatar?.let { imgAvatar.setImageResource(it) }
        tvName.text = user.name
        tvUsername.text = user.username
        btnFollower.text = user.follower.toString()
        btnFollowing.text = user.following.toString()
        tvCompany.text = user.company
        tvLocation.text = user.location
        tvRepo.text = user.repository + " Repository"

        supportActionBar?.title = "Detail User"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}