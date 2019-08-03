package com.carden.wanandroid_master.utils

import com.carden.wanandroid_master.common.Constants
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.*
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.nio.charset.Charset
import java.util.*
import java.util.concurrent.TimeUnit

/**
 *
 * 作者: carden
 * 时间: 2019/6/20 16:51
 * 描述: 网络请求简单封装
 *
 */
class NetUtils {
    companion object {
        var mRetrofit: Retrofit? = null
        var mOkHttpClient: OkHttpClient? = null
        var DEFAULT_TIMEOUT: Long = 60
        var DEFAULT_TIMEOUT_WRITE: Long = 60
        fun getOkhttpClient(): OkHttpClient? {
            if (null == mOkHttpClient) {
                synchronized(NetUtils::class.java) {
                    var builder: OkHttpClient.Builder = OkHttpClient.Builder()
                        .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                        .writeTimeout(DEFAULT_TIMEOUT_WRITE, TimeUnit.SECONDS)
                        .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                        .addInterceptor(LogInterceptor())
                        .retryOnConnectionFailure(true)
                    mOkHttpClient = builder.build()


                }
            }
            return mOkHttpClient

        }

        /**
         * 打印日志
         */
        class LogInterceptor : Interceptor {
            override fun intercept(chain: Interceptor.Chain?): Response? {

                val request: Request? = chain?.request()
                val url: String = request?.url().toString()
                val method: String = request?.method().toString()
                val t1: Long = System.nanoTime()
                LogUtils.getinstance()?.d(String.format("send %s and url [--%s]", method, url))
                val requestBody: RequestBody? = request?.body()
                if (null != requestBody) {
                    val stringBuilder: StringBuilder = StringBuilder("Request Body[")
                    val buffer: okio.Buffer = okio.Buffer()
                    requestBody.writeTo(buffer)
                    var charset: Charset = Charset.forName("UTF-8")
                    val contentType: MediaType = requestBody?.contentType()
                    contentType?.let {
                        charset = it.charset(charset)
                        if (isPainText(buffer)) {
                            stringBuilder.append(buffer.readString(charset))
                            stringBuilder.append(" (Content-Type = ").append(contentType.toString()).append(",")
                                .append(requestBody.contentLength()).append("-byte body)")
                        } else {
                            stringBuilder.append(" (Content-Type = ").append(contentType.toString())
                                .append(",binary ").append(requestBody.contentLength()).append("-byte body omitted)")
                        }

                    }
                    stringBuilder.append("]")
                    LogUtils.getinstance()?.d(String.format(Locale.getDefault(), "2 . %s"))


                }

                val response = chain?.proceed(request)
                val t2 = System.nanoTime()
                //the response time
                LogUtils.getinstance()?.w(
                    String.format(
                        Locale.getDefault(), "3. Received response for [url = %s] in %.1fms",
                        url, (t2 - t1) / 1e6
                    )
                )
                LogUtils.getinstance()?.w(
                    String.format(
                        Locale.CHINA,
                        "4. Received response is %s ,message[%s],code[%d]",
                        if (null != response && response.isSuccessful) "success" else "fail",
                        response?.message(),
                        response?.code()
                    )
                )
                //the response data
                val body = response?.body()

                val source = body?.source()
                source?.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
                val buffer = source?.buffer()
                var charset = Charset.defaultCharset()
                val contentType = body?.contentType()
                if (contentType != null) {
                    charset = contentType.charset(charset)
                }
                val bodyString = buffer?.clone()?.readString(charset)
                LogUtils.getinstance()?.w(String.format("5. Received response json string [%s]", bodyString))
                return response
            }
        }

        fun isPainText(buffer: Buffer): Boolean {
            try {
                val prefix: okio.Buffer = okio.Buffer()
                val count = buffer.let {
                    if (it.size() < 64) {
                        it.size()
                    } else {
                        64
                    }

                }
                LogUtils.getinstance()?.d("$count")
                buffer.copyTo(prefix, 0, count)
                for (i in 1..16) {
                    if (prefix.exhausted()) {
                        break
                    }
                    val codePoint = prefix.readUtf8CodePoint()
                    if (Character.isISOControl(codePoint) && Character.isWhitespace(codePoint)) {
                        return false
                    }


                }
                return true


            } catch (e: Exception) {
                e.printStackTrace()
                return false
            }

        }

        /**
         * retrofit初始化
         *
         */
        fun getRetrofit(): Retrofit? {
            if (null == mRetrofit) {
                synchronized(NetUtils::class.java) {
                    mRetrofit = Retrofit.Builder()
                        .baseUrl(Constants.URL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(getOkhttpClient())
                        .build()
                }
            }
            return mRetrofit

        }


    }

}