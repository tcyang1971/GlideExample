package tw.edu.pu.csim.tcyang.glideexample

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.PointF
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.bumptech.glide.request.target.CustomTarget
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.ColorFilterTransformation
import jp.wasabeef.glide.transformations.GrayscaleTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import jp.wasabeef.glide.transformations.gpu.ContrastFilterTransformation
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation
import jp.wasabeef.glide.transformations.gpu.SwirlFilterTransformation
import jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation

@GlideModule
public final class MyAppGlideModule : AppGlideModule()

class MainActivity : AppCompatActivity() {
    val multi = MultiTransformation<Bitmap>(
        //BlurTransformation(25),
        //RoundedCornersTransformation(128, 0, RoundedCornersTransformation.CornerType.ALL),
        //ColorFilterTransformation(Color.argb(80, 0, 0, 255)),
        //GrayscaleTransformation(),  //黑白
        //ToonFilterTransformation(), //卡通
        //ContrastFilterTransformation(2.0f), //對比
        //SketchFilterTransformation(), //速描
        SwirlFilterTransformation(0.5f, 1.0f, PointF(0.5f, 0.5f))  //旋轉
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val img: ImageView = findViewById(R.id.img)

        /*
        GlideApp.with(this)
            //.load(R.drawable.earth)
            .load("https://b.blog.xuite.net/b/b/5/2/11519069/blog_78644/txt/243420413/4.jpg")
            .placeholder(R.drawable.processing)
            .error(R.drawable.error)
            .listener(object: RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    Toast.makeText(baseContext, "圖片載入失敗", Toast.LENGTH_LONG).show()
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, dataSource: com.bumptech.glide.load.DataSource?, isFirstResource: Boolean): Boolean {
                    Toast.makeText(baseContext, "圖片載入成功", Toast.LENGTH_LONG).show()
                    return false
                }

            })

            //.circleCrop()
            //.override(800, 600)
            //.apply(RequestOptions.bitmapTransform(BlurTransformation(20, 2)))
            .apply(bitmapTransform(multi))
            .into(img)

         */

        GlideApp.with(this)
            .asBitmap()
            .load("https://b.blog.xuite.net/b/b/5/2/11519069/blog_78644/txt/243420413/4.jpg")
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                ) {
                    Toast.makeText(baseContext, "圖片載入成功", Toast.LENGTH_LONG).show()
                    img.setImageBitmap(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {

                }
            })
    }

}