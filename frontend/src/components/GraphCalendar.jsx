import React from 'react'
import Calendar from 'react-calendar'
import 'react-calendar/dist/Calendar.css';

export default function GraphCalendar({ setCalendarSelectedDate, calendarSelectedDate }) {

    return (
        <div>
            <Calendar onChange={setCalendarSelectedDate} value={calendarSelectedDate} />
        </div>
    );
}