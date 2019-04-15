package commands;

import java.io.Serializable;

import responses.BaseResponse;

public interface ICommand
{
    BaseResponse execute();
}
