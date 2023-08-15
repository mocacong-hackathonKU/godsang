//
//  DateUtils.swift
//  godsaeng
//
//  Created by Suji Lee on 2023/08/13.
//

import Foundation

//같은 날인지 검사
func isSameDay(date1: Date,date2: Date) -> Bool {
    let calendar = Calendar.current
    
    return calendar.isDate(date1, inSameDayAs: date2)
}

//년도와 월 구하기
func getYearAndMonth(currentDate: Date) -> [String] {
    
    let calendar = Calendar.current
    let month = calendar.component(.month, from: currentDate) - 1
    let year = calendar.component(.year, from: currentDate)
    
    return ["\(year)", calendar.monthSymbols[month]]
}

//현재 월 구하기
func getCurrentMonth(currentMonth: Int) -> Date {
    
    let calendar = Calendar.current
    guard let currentMonth = calendar.date(byAdding: .month, value: currentMonth, to: Date()) else{
        return Date()
    }
    
    return currentMonth
}

//날짜 추출
func extractDate(currentMonth: Int) -> [DateValue] {
    
    let calendar = Calendar.current
    let currentMonth = getCurrentMonth(currentMonth: currentMonth)
    var days = currentMonth.getAllDates().compactMap { date -> DateValue in
        let day = calendar.component(.day, from: date)
        return DateValue(day: day, date: date)
    }
    let firstWeekday = calendar.component(.weekday, from: days.first!.date)
    
    for _ in 0..<firstWeekday - 1{
        days.insert(DateValue(day: -1, date: Date()), at: 0)
    }
    
    return days
}

extension Date {
    func getAllDates() -> [Date] {
        let calendar = Calendar.current
        let startDate = calendar.date(from: Calendar.current.dateComponents([.year,.month], from: self))!
        let range = calendar.range(of: .day, in: .month, for: startDate)!
        return range.compactMap { day -> Date in
            return calendar.date(byAdding: .day, value: day - 1, to: startDate)!
        }
    }
}
