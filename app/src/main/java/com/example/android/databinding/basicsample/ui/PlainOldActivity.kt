/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.databinding.basicsample.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.android.databinding.basicsample.R
import com.example.android.databinding.basicsample.data.SimpleViewModel

class PlainOldActivity : AppCompatActivity() {

  // Obtain ViewModel from ViewModelProviders
  private val viewModel by lazy { ViewModelProviders.of(this).get(SimpleViewModel::class.java) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.plain_activity)

    updateName()
    updateLikes()
  }

  /**
   * This method is triggered by the `android:onclick` attribute in the layout. It puts business
   * logic in the activity, which is not ideal. We should do something about that.
   */
  fun onLike(view: View) {
    viewModel.onLike()
    updateLikes()
  }

  /**
   * So much findViewById! We'll fix that with Data Binding.
   */
  private fun updateName() {
    findViewById<TextView>(R.id.plain_name).text = viewModel.name
    findViewById<TextView>(R.id.plain_lastname).text = viewModel.lastName
  }

  /**
   * This method has many problems:
   * - It's calling findViewById multiple times
   * - It has untestable logic
   * - It's updating two views when called even if they're not changing
   */
  private fun updateLikes() {
    findViewById<TextView>(R.id.likes).text = viewModel.likes.toString()
    findViewById<ProgressBar>(R.id.progressBar).progress =
      (viewModel.likes * 100 / 5).coerceAtMost(100)
  }
}
