package br.com.rodrigo.naoreveze.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.databinding.ResTabelaImcBinding

class TabelaImc(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val binding : ResTabelaImcBinding = ResTabelaImcBinding.inflate(LayoutInflater.from(context)
    , this, true
    )

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.TabelaImc)


        val tint = attributes.getColorStateList(R.styleable.TabelaImc_circle_color)
        val drawable = binding.resultCircle.drawable
        drawable?.setTintList(tint)
        binding.resultCircle.setImageDrawable(drawable)

        this.binding.resultText.text = attributes.getString(R.styleable.TabelaImc_result_text)
        this.binding.tableTextNumber.text = attributes.getString(R.styleable.TabelaImc_table_text_number)

        attributes.recycle()

    }
}