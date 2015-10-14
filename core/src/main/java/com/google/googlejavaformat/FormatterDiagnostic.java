/*
 * Copyright 2015 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.googlejavaformat;

import com.google.common.base.Preconditions;

/** An error that prevented formatting from succeeding. */
public class FormatterDiagnostic {
  private final String filename;
  private final int lineNumber;
  private final String message;
  private final int column;

  public FormatterDiagnostic(String filename, int lineNumber, int column, String message) {
    Preconditions.checkArgument(lineNumber >= 0);
    Preconditions.checkArgument(column >= 0);
    Preconditions.checkNotNull(filename);
    Preconditions.checkNotNull(message);

    this.filename = filename;
    this.lineNumber = lineNumber;
    this.column = column;
    this.message = message;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(filename).append(':');
    sb.append(lineNumber).append(':');
    // internal column numbers are 0-based, but diagnostics use 1-based indexing by convention
    sb.append(column + 1).append(':');
    sb.append(' ');
    sb.append("error: ").append(message);
    return sb.toString();
  }
}
