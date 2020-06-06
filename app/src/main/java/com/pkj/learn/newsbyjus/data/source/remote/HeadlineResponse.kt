package com.pkj.learn.newsbyjus.data.source.remote

/**
 * @author Pankaj Jangid
 */
/*{
    "status": "ok",
    "totalResults": 70,
    -"articles":[
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
    ]
  }
*/
data class HeadlineResponse(
    val status: String = "",
    val totalResults: Int = 0,
    val articles: List<Article> = emptyList()
)


data class Article(
    val source: Source = Source(),
    val author: String = "",
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val urlToImage: String = "",
    val publishedAt: String = "",
    val content: String = ""
)

data class Source(
    val id: String = "",
    val name: String = ""
)