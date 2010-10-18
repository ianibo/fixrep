/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.k_int.fixrep

/**
 *
 * @author ibbo
 */
class FixRepPluginResult {
  
  String code
  String status
  String message
  Long elapsed
  List terms = []

  def tostring() { "${code} - ${terms}" }
}

