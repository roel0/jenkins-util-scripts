package com.roel0.jenkinsutils


class PipelineUtils
{
    static def failed_stages = []

    static def register_failed_stage(name)
    {
        failed_stages << name
    }

    static def get_failed_stages()
    {
        return failed_stages
    }
}
