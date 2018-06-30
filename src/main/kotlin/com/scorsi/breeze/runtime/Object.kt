package com.scorsi.breeze.runtime

typealias KNumber = kotlin.Number
typealias KString = kotlin.String
typealias BFunArgs = Map<String, Class<BObject>>
typealias BFunBody = (Map<String, BObject>) -> BObject

abstract class BObject {
    private val attributes: MutableMap<KString, BObject> = mutableMapOf()
    private var const: Boolean = true
    fun getAttribute(name: KString) = attributes.getOrElse(name) { throw Error() }
    fun setAttribute(name: KString, value: BObject) = when (const) {
        true -> throw Error()
        false -> attributes[name] = value
    }
}

abstract class BType<out KType>(val value: KType) : BObject() {
    init {
        setAttribute("get", BFunction(mapOf()) { _ -> this })
    }
}

class BNumber(value: KNumber) : BType<KNumber>(value)
class BString(value: KString) : BType<KString>(value)
class BFunction(args: BFunArgs, body: BFunBody) : BType<Pair<BFunArgs, BFunBody>>(Pair(args, body))
