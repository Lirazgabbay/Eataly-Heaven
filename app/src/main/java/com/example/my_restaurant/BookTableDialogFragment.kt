import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.example.my_restaurant.MainActivity
import com.example.my_restaurant.R
import java.util.Calendar
import androidx.core.content.ContextCompat
/**
 * The BookTableDialogFragment class represents a dialog fragment in the restaurant
 * booking system. It allows users to book a table by providing their name, contact
 * information, preferred date, seating preferences, and payment method. Users can also
 * indicate whether they have dietary restrictions and agree to the terms and conditions.
 * The dialog includes input fields, radio buttons, checkboxes, spinners, and buttons
 * for user interaction. Additionally, it validates user input and displays appropriate
 * error messages if necessary.
 **/
class BookTableDialogFragment : DialogFragment() {

    private var selectedDate = ""
    private var veganSelection: Boolean = false
    private var paymentMethod: Boolean = false
    private var agreedToTerms: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.dialog_book_table, container, false)
        // Find the arrow back icon ImageView
        val arrowBackIcon: ImageView = view.findViewById(R.id.arrow_back_icon)
        // Set click listener on the arrow back icon
        arrowBackIcon.setOnClickListener {
            // Navigate back to MainActivity when arrow icon is clicked
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            // Dismiss the dialog fragment
            dismiss()
        }
        val editTextFullName = view.findViewById<EditText>(R.id.editTextFullName)
        editTextFullName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    editTextFullName.setBackgroundResource(R.drawable.dashed_underline)
                } else {
                    editTextFullName.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.holo_green_light))
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
              //do nothing
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               //do nothing
            }
        })
        // Set click listener for the vegan RadioButton
        view.findViewById<RadioButton>(R.id.radio_vegan).setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                veganSelection=true
                // Set text color of vegan button to green
                buttonView.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_green_light))
                // Reset text color of not vegan button to default
                view.findViewById<RadioButton>(R.id.radio_not_vegan).setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
            }
        }
        // Set click listener for the not vegan RadioButton
        view.findViewById<RadioButton>(R.id.radio_not_vegan).setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                veganSelection=true
                // Set text color of not vegan button to green
                buttonView.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_green_light))
                // Reset text color of vegan button to default
                view.findViewById<RadioButton>(R.id.radio_vegan).setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
            }
        }
        // Set click listener for the terms CheckBox
        view.findViewById<CheckBox>(R.id.checkBoxTerms).setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                agreedToTerms=true
                view.findViewById<CheckBox>(R.id.checkBoxTerms).setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.holo_green_light))
            }
            else{
                view.findViewById<CheckBox>(R.id.checkBoxTerms).setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.white))
            }
        }
        // Set click listener for the cash RadioButton
        view.findViewById<RadioButton>(R.id.radio_cash).setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                paymentMethod=true
                // Set text color of cash button to green
                buttonView.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_green_light))
                // Reset text color of not vegan button to default
                view.findViewById<RadioButton>(R.id.radio_card).setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
            }
        }
        // Set click listener for the card RadioButton
        view.findViewById<RadioButton>(R.id.radio_card).setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                paymentMethod=true
                // Set text color of not vegan button to green
                buttonView.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_green_light))
                // Reset text color of vegan button to default
                view.findViewById<RadioButton>(R.id.radio_cash).setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
            }
        }
        val spinner: Spinner = view.findViewById(R.id.seat_selection_spinner)
        val adapter = ArrayAdapter.createFromResource(requireContext(), R.array.seat_numbers, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        // Set item selected listener for the spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Handle selection
                val selectedItem = parent?.getItemAtPosition(position).toString()
                // Show the selected item in a toast message using the string resource
                Toast.makeText(requireContext(), getString(R.string.selected_item) + ": $selectedItem", Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
        // Set click listener for reserve seats button
        view.findViewById<Button>(R.id.reserveSeatsButton).setOnClickListener {
            reserveSeats()
        }
        // Set click listener for the date selection button
        view.findViewById<Button>(R.id.buttonPickDate).setOnClickListener {
            showDatePickerDialog()
        }
        return view
    }

    //button date
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set click listener for the date selection button
        view.findViewById<Button>(R.id.buttonPickDate).setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                val buttonPickDate = view?.findViewById<Button>(R.id.buttonPickDate)
                buttonPickDate?.text = selectedDate
                // Change the background color of the button to green
                buttonPickDate?.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        android.R.color.holo_green_light
                    )
                )
            },
            year,
            month,
            dayOfMonth
        )

    // Set minimum date to today's date
        datePickerDialog.datePicker.minDate = calendar.timeInMillis
        datePickerDialog.show()
    }

        private fun reserveSeats() {
        //extracting parameters from a dialog
        val fullName = dialog?.findViewById<EditText>(R.id.editTextFullName)?.text.toString()
        val email = dialog?.findViewById<EditText>(R.id.editTextEmail)?.text.toString()
        val phoneNumber = dialog?.findViewById<EditText>(R.id.editTextPhoneNumber)?.text.toString()
        val seatSelection = dialog?.findViewById<Spinner>(R.id.seat_selection_spinner)?.selectedItem.toString()
        val time = dialog?.findViewById<Spinner>(R.id.time_display)?.selectedItem.toString()
        //check if there is an empty field
        if (fullName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || selectedDate=="" || seatSelection.isEmpty() || time.isEmpty() || !veganSelection || !paymentMethod || !agreedToTerms) {
            // Notify the user to fill in all the required fields
            AlertDialog.Builder(requireContext()).apply {
                setTitle(R.string.incomplete_information_title)
                setMessage(R.string.fill_required_fields_message)
                setPositiveButton(android.R.string.ok, null)
                show()
            }
            return
        }
        //validate full name
        val names = fullName.split(" ")
        if (names.size < 2 || names[0].isEmpty() || names[1].isEmpty()) {
            AlertDialog.Builder(requireContext()).apply {
                setTitle(R.string.invalid_full_name_title)
                setMessage(R.string.invalid_full_name_message)
                setPositiveButton(android.R.string.ok, null)
                show()
            }
            return
        }
        // Validate email format
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // Notify the user of invalid email format
            AlertDialog.Builder(requireContext()).apply {
                setTitle(R.string.invalid_email_title)
                setMessage(R.string.invalid_email_message)
                setPositiveButton(android.R.string.ok, null)
                show()
            }
            return
        }
            // Validate phone number format
            val phoneNumberPattern = "\\d{10}".toRegex()
            if (!phoneNumberPattern.matches(phoneNumber)) {
                // Notify the user of invalid phone number format
                AlertDialog.Builder(requireContext()).apply {
                    setTitle(R.string.invalid_phone_number_title)
                    setMessage(R.string.invalid_phone_number_message)
                    setPositiveButton(android.R.string.ok, null)
                    show()
                }
                return
            }
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.apply {
            setTitle(R.string.reservation_details_title)
            setMessage(
                getString(R.string.full_name_label) + ": " + fullName + "\n" +
                        getString(R.string.email_label) + ": " + email + "\n" +
                        getString(R.string.phone_number_label) + ": " + phoneNumber + "\n" +
                        getString(R.string.seat_selection_label) + ": " + seatSelection + " " + getString(R.string.people_label) + "\n" +
                        getString(R.string.selected_date_label) + ": " + selectedDate + " " + time + "\n" +
                        getString(R.string.vegan_label) + ": " + if (veganSelection) getString(R.string.yes_label) else getString(R.string.no_label) + "\n" +
                        getString(R.string.payment_label) + ": " + if (paymentMethod) getString(R.string.cash_label) else getString(R.string.credit_card_label) + "\n\n" +
                        getString(R.string.confirm_reservation_message)
            )
            setPositiveButton(R.string.confirm_button) { dialog, _ ->
                AlertDialog.Builder(requireContext()).apply {
                    setTitle(R.string.reservation_accepted_title)
                    setMessage(R.string.reservation_accepted_message)
                    setPositiveButton(android.R.string.ok, null)
                    show()
                }
                dialog.dismiss()
            }
            setNegativeButton(R.string.cancel_button) { dialog, _ ->
                dialog.dismiss()
            }
            show()
        }
    }
}
