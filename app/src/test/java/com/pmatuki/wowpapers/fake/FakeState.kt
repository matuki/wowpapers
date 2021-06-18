package com.pmatuki.wowpapers.fake

sealed class FakeState {

    object Normal: FakeState()

    object ThrowError : FakeState()

    object ReturnEmpty: FakeState()
}
