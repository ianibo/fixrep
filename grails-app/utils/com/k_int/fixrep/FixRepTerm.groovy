/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.k_int.fixrep

/**
 *
 * @author ibbo
 */
class FixRepTerm {
  
  String termSource
  String termType
  String termString
  String termURI
  String termIdentifier
  String termVector // Pointer to source
  String termReason // Reason for inclusion if known
  Double termConfidence

  String toString() { "${termSource} ${termType} ${termString}" }
}

