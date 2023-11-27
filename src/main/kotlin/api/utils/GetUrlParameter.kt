package api.utils

import io.ktor.server.application.*
import io.ktor.util.pipeline.*

fun PipelineContext<*, ApplicationCall>.getUrlParameter(name: String): String? {
    return call.request.queryParameters[name]
}