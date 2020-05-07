package com.hbisoft.hbrecorder.events


import java.util.ArrayList


class HBRecorderObserversSubscriber private constructor(): HBRecorderObserver {


    /**
     * Will subscribe an observer to any changes
     */
    fun subscribeObserver(newObserverView: HBRecorderObserver) {
        if (observersList == null) observersList = ArrayList()
        if (observersList!!.contains(newObserverView)) return
        observersList!!.add(newObserverView)
    }

    /**
     * Will unsubscribe an observer from any changes
     */
    fun unsubscribeObserver(observerView: HBRecorderObserver) {
        if (observersList == null || !observersList!!.contains(observerView)) return
        observersList!!.remove(observerView)
    }

    fun clearAllObservers() {
        observersList!!.clear()
    }

    private object HOLDER {
        val INSTANCE = HBRecorderObserversSubscriber()
    }

    companion object {
        private var observersList: ArrayList<HBRecorderObserver>? = null
        val instance: HBRecorderObserversSubscriber by lazy { HOLDER.INSTANCE }
    }

    override fun onPause() {
        observersList?.forEach { it.onPause() }
    }

    override fun onResume() {
        observersList?.forEach { it.onResume() }
    }
}
