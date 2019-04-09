/*************************************************************************
 *
 * ZIVAME CONFIDENTIAL
 * ___________________
 *
 *  (C) 2019 Actoserba
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Actoserba and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary may be covered by India and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Actoserba.
 */
package com.zivame.sample.utility;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author rajivranjan
 * @version 1.0
 * @organization Actoserba
 * @Date 06-Apr-2019
 */
public class Helper {

	public static <T> String collapseList(Collection<T> illegalValue, String delimiter) {
		if (delimiter == null) {
			delimiter = "";
		}
		if (null != illegalValue) {
			StringBuilder builder = new StringBuilder();
			Iterator<T> iter = illegalValue.iterator();
			while (iter.hasNext()) {
				builder.append(iter.next());
				if (iter.hasNext()) {
					builder.append(delimiter);
				}
			}
			return builder.toString();
		}
		return "";

	}

}
