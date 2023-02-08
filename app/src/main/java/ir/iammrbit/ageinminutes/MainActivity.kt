package ir.iammrbit.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDatePicker.setOnClickListener {view:View ->
            clickDatePicker(view)
        }
    }

    private fun clickDatePicker(view : View){
        //Date format : dd/MM/yyyy
        val sdf = SimpleDateFormat("dd/MM/yyyy" , Locale.ENGLISH)
        //Calender to get day month and year to pass to the "dpd"
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val dayOfMonth = myCalender.get(Calendar.DAY_OF_MONTH)
        //current date
        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
        //date picker dialog to take date of birth from user
        val dpd = DatePickerDialog(this ,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this,
                    "The chosen year is $selectedYear , the month is ${selectedMonth+1} and the day is $selectedDayOfMonth" ,
                    Toast.LENGTH_SHORT).show()
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                tvSelectedDate.text = selectedDate
                val theDate = sdf.parse(selectedDate)
                //Dates in minutes
                val selectedDateInMinutes = theDate!!.time / 60000
                val currentDateInMinutes = currentDate!!.time/60000
                //calculate result in MINUTES : difference of current date and selected date
                val differenceInMinutes = (currentDateInMinutes - selectedDateInMinutes)
                //set result in MINUTES
                tvResultInMinutes.text = differenceInMinutes.toString()},
            //pass arguments
            year ,month,dayOfMonth )
        //limit the date selection to now or past
        dpd.datePicker.setMaxDate(Date().time-8640000)
        //show date picker dialog to take a date from user
        dpd.show()
    }
}