package com.finnect.workspace.application.port.out;

import com.finnect.workspace.MemberState;

public interface ExitWorkspacePort {

    boolean exit(MemberState memberState);
}
