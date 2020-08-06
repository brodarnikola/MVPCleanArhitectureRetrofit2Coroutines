package com.vjezba.mvpcleanarhitecturegithub.core.entities


data class RepositoryDetails(val id: Int, val owner: RepositoryOwnerDetails = RepositoryOwnerDetails(""),
                             val full_name: String, val description: String, val stargazers_count: String,
                             val watchers_count: String, val forks: String, val open_issues: Int
)