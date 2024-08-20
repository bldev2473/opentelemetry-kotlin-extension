package com.bldev.otel.extension.span.util

import io.opentelemetry.api.GlobalOpenTelemetry

class SpanHandler {
  companion object {
    suspend fun <T> spanning(
      scopeName: String,
      spanName: String,
      func: suspend () -> T
    ): T {
      val tracer = GlobalOpenTelemetry.getTracer(scopeName)
      val spanBuilder = tracer.spanBuilder(spanName)

      val span = spanBuilder.startSpan()
      span.makeCurrent()

      val result = func.invoke()
      span.setAttribute("result", result.toString())

      span.end()

      return result
    }
  }
}
