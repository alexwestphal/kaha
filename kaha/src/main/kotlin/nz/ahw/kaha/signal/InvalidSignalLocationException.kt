/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha.signal

import nz.ahw.kaha.KahaException

class InvalidSignalLocationException(signal: Signal): KahaException("A Signal can't be sent within a response. Offending Signal: $signal")