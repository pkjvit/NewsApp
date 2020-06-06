package com.pkj.learn.newsbyjus.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * @author Pankaj Jangid
 */

/*
    {
        -"source": {
        "id": null,
        "name": "Bitcoinist"
    },
        "author": "Nick Chong",
        "title": "We Are Near a “Plateau” for Ethereum DeFi Due to Latency Issues: Top Investor",
        "description": "Decentralized finance has rapidly become Ethereum’s “killer use case,” many analysts have recently said. DeFi, as the sub-sector is now known as, contains nearly $1 billion worth of locked value, along with some of the industry’s most prominent investors and …",
        "url": "https://bitcoinist.com/plateau-ethereum-defi-latency-issues-investor/",
        "urlToImage": "https://bitcoinist.com/wp-content/uploads/2020/06/shutterstock_232639537-1920x1280.jpg",
        "publishedAt": "2020-06-05T12:00:51Z",
        "content": "Decentralized finance has rapidly become Ethereum’s “killer use case,” many analysts have recently said. DeFi, as the sub-sector is now known as, contains nearly $1 billion worth of locked value, alo… [+3929 chars]"
    }
*/

@Entity(tableName = "Article")
class Article @JvmOverloads constructor(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long = UUID.randomUUID().getMostSignificantBits() and Long.MAX_VALUE,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "publishedAt")
    val publishedAt: String

)