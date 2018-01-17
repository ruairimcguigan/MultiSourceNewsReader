package com.aquidigital.multisource.newsreader.data.model

import com.fasterxml.jackson.annotation.JsonIgnore
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Source : RealmObject() {

	@PrimaryKey
	lateinit var id: String
	lateinit var name: String

	@JsonIgnore
	var lastSyncDate = 0L
}