package com.finnect.mockDomain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "workspace")
public class WorkspaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workspaceId;
}
