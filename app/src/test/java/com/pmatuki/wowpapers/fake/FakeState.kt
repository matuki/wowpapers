package com.pmatuki.wowpapers.fake

internal sealed class FakeState {

    object Normal: FakeState()

    object ThrowError : FakeState()

    object ReturnEmpty: FakeState()
}
