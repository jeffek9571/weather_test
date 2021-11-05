package com.jwisdom.mykotlin.data.xml

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root


//@Root(name = "note")
//data class Content @JvmOverloads constructor(
//    @field:Element(name = "from")
//    var from: String = "",
//    @field:Element(name = "to")
//    var to: String = "",
//    @field:Element(name = "message")
//    var message: String = "",
//)

@Root(name = "note")
data class Content (
    @field:Element(name = "from")
    var from: String = "",
    @field:Element(name = "to")
    var to: String = "",
    @field:Element(name = "message")
    var message: String = "",
)
