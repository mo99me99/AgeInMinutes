package ir.iammrbit.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigInteger
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
//    private val aMinute : Long = 1000*60
//    private val aHour : Long = aMinute*60
//    private val aDay :Long = aHour*24
//    private val aMonth = aDay*30
//    private val aYear:Long = aMonth*12



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
                val selectedDate = "$selectedDayOfMonth/$selectedMonth/$selectedYear"
                tvSelectedDate.text = selectedDate
                val theDate = sdf.parse(selectedDate)
                val selectedDateInMilliSecond:Long = theDate!!.time
                val currentDateInMilliSeconds:Long = currentDate!!.time
                //result in years
                yearsCal(selectedDateInMilliSecond , currentDateInMilliSeconds)
               //result in months
                monthCal(selectedDateInMilliSecond , currentDateInMilliSeconds)
                //TODO result in days
                //TODO result in hours
                //TODO result in minutes
                minutesCal(selectedDateInMilliSecond , currentDateInMilliSeconds)
                // result in seconds
                secondCal(selectedDateInMilliSecond , currentDateInMilliSeconds)
                                               },
            //pass arguments
            year ,month,dayOfMonth )
        //limit the date selection to now or past
        dpd.datePicker.setMaxDate(Date().time-8640000)
        //show date picker dialog to take a date from user
        dpd.show()
    }
    private fun yearsCal(selectedDate: Long , currentDate: Long){
        val aYear =  31_536_000_000//1000*60*60*24*365
        val diffInYear = (currentDate-selectedDate)/aYear
        tvResultInYears.text = diffInYear.toString()
    }

    private fun monthCal(selectedDate:Long  , currentDate:Long){
        //calculate how a month longs
        val aMonth  = 2_592_000_000//1000*60*60*24*30
        //calculate result in MONTH : difference of current date and selected date
        val difference = ((currentDate -selectedDate)/aMonth)-1

        //set result in MONTHS
        tvResultInMonths.text = difference.toString()

    }

    private fun minutesCal(selectedDate : Long , currentDate:Long){
        //calculate how a minute longs
        val aMinute = 1000*60
        //calculate result in MINUTES : difference of current date and selected date
        val difference = (currentDate - selectedDate)/aMinute
        //set result in MINUTES
        tvResultInMinutes.text = difference.toString()
    }
    private fun secondCal(selectedDate : Long , currentDate:Long){
        //calculate how a minute longs
        val aSecond= 1000
        //calculate result in MINUTES : difference of current date and selected date
        val difference = ((currentDate - selectedDate)/aSecond)
        //set result in MINUTES
        tvResultInSeconds.text = difference.toString()
    }

}