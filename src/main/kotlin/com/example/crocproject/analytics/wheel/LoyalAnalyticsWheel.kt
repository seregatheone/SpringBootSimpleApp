package com.example.crocproject.analytics.wheel

import com.example.crocproject.data.models.wheel.WheelGoodsRequestBody



//from scipy.optimize import minimize
//
//from math import sqrt
//
//# ########################
//# #цена лота
//# price=2000
//# #бюджет
//# budget = 2000
//# #число билетов
//# tickets = 10
//# ########################
//
//
//########################
//#цена лота
//price=500
//#бюджет
//budget = 5000
//#число билетов
//tickets = 50
//########################
//
//
//
//
//# красиво печатаем
//def prettify_disc(disc):
//    s = ""
//    for d in disc:
//        s+=f"{d*5}%:{disc[d]};\n"
//    return s
//
//# генерируем скидки
//def generate_discs(arr):
//    a,b=arr
//    # виды скидок
//    # i*5% - скидка, т.е 1 - 5%, 2 - 10%, 10 - 50% ...
//    disc = {i:a*b**-(i) for i in [i for i in range(1,17)]}
//    return disc
//
//# генерируем скидки
//def generate_discs_int(arr):
//    a,b=arr
//    # виды скидок
//    # i*5% - скидка, т.е 1 - 5%, 2 - 10%, 10 - 50% ...
//    disc = {i:int(a*b**-(i)) for i in [i for i in range(1,17)]}
//    return disc
//
//# потраченный бюджет
//def calc_spent(disc):
//    return sum([disc[i]*i*price/20 for i in disc])
//
//# лосс-функция для градиентного спуска
//def eval_disc(disc):
//    # соотношение веса цены и бюджета для оптимизации
//    WEIGHT_BUDGET=1
//    WEIGHT_NUMBER=10
//
//    db = budget - calc_spent(disc)
//    loss_b = abs(db)**2
//
//    dn = sum(disc.values())-tickets
//    loss_n = abs(dn)**2
//
//    return WEIGHT_BUDGET*loss_b + WEIGHT_NUMBER*loss_n
//
//
//def eval_arr(arr):
//    discs = generate_discs(arr)
//    loss = eval_disc(discs)
//    return loss
//
//#################################
//
//arr_0 = [tickets/2, 1.5]
//
//res = minimize(eval_arr, arr_0, method='BFGS')
//
//arr = res.x
//
//
//
//###############################
//discs = generate_discs_int(arr)
//
////
//ntickets = sum(discs.values())
//discs_ = discs.copy()
//discs_[1]+=tickets-ntickets
//if(calc_spent(discs_)<=budget):
//    discs = discs_
//##################################
//
//
//print(discs)
//print("Tickets:",  sum(discs.values()))
//print("Spent:", calc_spent(discs))
//print("Details:\n",prettify_disc(discs))
//
//show_disc(discs)

class LoyalAnalyticsWheel(wheelGoodsRequestBody : WheelGoodsRequestBody) {
//    val items = wheelGoodsRequestBody.fortuneItems
//    val budget = wheelGoodsRequestBody.computationBudget
//
//
//    val bfgs = BFGS()
//    arr_0 = [tickets/2, 1.5]
//    res = minimize(eval_arr, arr_0, method='BFGS')
//    //generate discounts
//    a,b=arr
//    disc = {i:a*b**-(i) for i in [i for i in range(1,17)]}
//    arr = res.x
//    //
//    discs = generate_discs_int(arr)
//
//    ntickets = sum(discs.values())
//    discs_ = discs.copy()
//    discs_[1]+=tickets-ntickets
//    if(calc_spent(discs_)<=budget):
//    discs = discs_
//
    companion object{
        const val WEIGHT_BUDGET=1
        const val WEIGHT_NUMBER=10
    }
}